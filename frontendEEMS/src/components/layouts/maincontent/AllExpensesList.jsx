import React, { useState, useEffect } from 'react'
import { FaTrash } from 'react-icons/fa'
import axios from 'axios';
import { toast } from 'react-toastify';

const AllExpensesList = () => {
  const [expenses, setExpenses] = useState([]);

  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchExpenses = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/admin/viewAllExpenses', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setExpenses(response.data);
      } catch (error) {
        console.error('Error fetching expenses:', error);
      }
    };
    fetchExpenses();
  }, []);

  const getInvoiceUrl = (invoicePath) => {
    const filename = invoicePath.split('\\').pop(); 
    return `http://localhost:8080/api/dashboard/view-invoice/${encodeURIComponent(filename)}`;
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this expense?");
    if (!confirmDelete)
      return;
    try{
    const response = await axios.delete(`http://localhost:8080/api/employee/delete-expense/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    toast.success("Expense deleted!");
    setExpenses(expenses.filter(exp => exp.id !== id)); // Update state to remove deleted expense
  }catch (error){
    alert("Only pending expenses can be deleted!" );
  }
    fetchExpenses(); // Refresh the list after delete
  };

  return (
    <>
    <h2 className='emp-expenselist-heading'>All Expenses</h2>
              <div className='emp-expenselist'>
                <table className='expenselist-table'>
                  <thead>
                    <tr>
                      <th style={{width:"5%"}}>S.No.</th>
                      <th style={{width:"5%"}}>#ExpenseID</th>
                      <th style={{width:"15%"}}>Name</th>
                      <th style={{width:"10%"}}>Category</th>
                      <th style={{width:"20%"}}>Description</th>
                      <th style={{width:"10%"}}>Amount</th>
                      <th style={{width:"5%"}}>Invoice</th>
                      <th style={{width:"10%"}}>Status</th>
                      <th style={{width:"10%"}}>Date</th>
                      <th style={{width:"5%"}}>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {expenses.map((exp, index) =>(
                    <tr key={exp.id}>
                      <td>{index+1}</td>
                      <td>{exp.id}</td>
                      <td>{exp.employee.full_name}</td>
                      <td>{exp.category}</td>
                      <td>{exp.description}</td>
                      <td>₹{exp.amount}</td>
                      <td>
                      <a href={getInvoiceUrl(exp.invoice)} target='_blank' rel='noopener noreferrer'>View</a>
                      </td>
                      <td>{exp.status}</td>
                      <td>{new Date(exp.date).toLocaleDateString()}</td>
                      <td>
                          <FaTrash className='delete-btn' onClick={() => handleDelete(exp.id)}/>
                      </td>
                    </tr> 
                  ))}            
                  </tbody>
                </table>
              </div>
    </>
  )
}

export default AllExpensesList
