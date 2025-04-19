import React, { useEffect, useState } from 'react';
import '@/components/styles/homelayout.css';
import { FaEdit, FaTrash } from 'react-icons/fa';
import axios from 'axios';
import { istokenExpired } from '../../../utils/auth';
import { useNavigate } from 'react-router-dom';
import EditExpenseModal from './EditExpenseModal';

const EmployeeViewExpense = () => {
  const [expenses, setExpenses] = useState([]);
  const [editingExpense, setEditingExpense] = useState(null);
  const navigate = useNavigate();

  const token = localStorage.getItem('token');
  if (!token || istokenExpired(token)) {
    navigate('/');
  }

  const fetchExpenses = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/employee/view-expense', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setExpenses(response.data);
    } catch (error) {
      console.error('Error fetching expenses:', error);
    }
  };

  useEffect(() => {
    fetchExpenses();
  }, []);

  const getInvoiceUrl = (invoicePath) => {
    if (!invoicePath) return null;
    const filename = invoicePath.split(/[/\\]/).pop(); // handles both / and \
    return `http://localhost:8080/api/dashboard/view-invoice/${encodeURIComponent(filename)}`;
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this expense?");
    if (!confirmDelete)
      return;
    try {
      const response = await axios.delete(`http://localhost:8080/api/employee/delete-expense/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      window.alert("Expense deleted!");
      setExpenses(expenses.filter(exp => exp.id !== id)); // Update state to remove deleted expense
    } catch (error) {
      alert("Only pending expenses can be deleted!");
    }
    fetchExpenses(); // Refresh the list after delete
  };

  return (
    <>
      <h2 className='emp-expenselist-heading'>Your Expense List</h2>
      <div className='emp-expenselist'>
        <table className='expenselist-table'>
          <thead>
            <tr>
              <th style={{ width: "5%" }}>S.No.</th>
              <th style={{ width: "10%" }}>#ExpenseID</th>
              <th style={{ width: "10%" }}>Category</th>
              <th style={{ width: "35%" }}>Description</th>
              <th style={{ width: "10%" }}>Amount</th>
              <th style={{ width: "15%" }}>Invoice</th>
              <th style={{ width: "15%" }}>Date</th>
              <th style={{ width: "10%" }}>Action</th>
            </tr>
          </thead>
          <tbody>
            {expenses.map((exp, index) => (
              <tr key={exp.id}>
                <td>{index + 1}</td>
                <td>{exp.id}</td>
                <td>{exp.category}</td>
                <td>{exp.description}</td>
                <td>₹{exp.amount}</td>
                <td>
                  {exp.invoice ? (
                    <a href={getInvoiceUrl(exp.invoice)} target="_blank" rel="noopener noreferrer">
                      View
                    </a>) : (<span style={{ color: 'gray' }}>No Invoice</span>)}
                </td>
                <td>{new Date(exp.date).toLocaleDateString()}</td>
                <td>
                  <FaEdit className='edit-btn' onClick={() => setEditingExpense(exp)} />
                  <FaTrash className='delete-btn' onClick={() => handleDelete(exp.id)} />
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {editingExpense && (
          <EditExpenseModal
            expense={editingExpense}
            onClose={() => setEditingExpense(null)}
            onSave={(updatedExpense) => {              
              setExpenses(prev => prev.map(e => e.id === updatedExpense.id ? updatedExpense : e));
              fetchExpenses(); 
              setEditingExpense(null);
            }} />
        )}
      </div>
    </>
  );
};

export default EmployeeViewExpense;
