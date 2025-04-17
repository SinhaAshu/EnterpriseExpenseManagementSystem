import React, { useState, useEffect} from 'react'
import '@/components/styles/homelayout.css'
import { FaCheck, FaTimes } from 'react-icons/fa'
import axios from 'axios';
import { istokenExpired } from '../../../utils/auth';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const AdminExpenseRequests = () => {
    const navigate = useNavigate();
    const [expenses, setExpenses] = useState([]);
  
    const token = localStorage.getItem('token');
    if (!token || istokenExpired(token)) {
      navigate('/');
    }
  
    useEffect(() => {
      const fetchExpenses = async () => {
        try {
          const response = await axios.get('http://localhost:8080/api/admin/expense/requests', {
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
  
    const handleStatusUpdate = async (id, status) => {
      const confirmMsg = `Are you sure you want to ${status.toLowerCase()} this expense?`;
      const confirmAction = window.confirm(confirmMsg);
    
      if (!confirmAction) return;
    
      try {
        const response = await axios.put(
          `http://localhost:8080/api/dashboard/update-status/${id}?status=${status}`,
          {},
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        window.alert(response.data.message);
        setExpenses(expenses.filter((exp) => exp.id !== id));
      } catch (error) {
        toast.error(`Error updating status: ${error.response?.data?.message || error.message}`);
        console.error("Action cannot be performed!");
      }
    };
    
    return (
      <>
      <h2 className='emp-expenselist-heading'>Pending Requests</h2>
              <div className='emp-expenselist'>
                <table className='expenselist-table'>
                  <thead>
                    <tr>
                      <th style={{ width:"5%" }}>S.No.</th>
                      <th style={{width:"5%"}}>#ExpenseID</th>
                      <th style={{width:"15%"}}>Name</th>
                      <th style={{width:"10%"}}>Category</th>
                      <th style={{width:"20%"}}>Description</th>
                      <th style={{width:"10%"}}>Amount</th>
                      <th style={{width:"5%"}}>Invoice</th>
                      <th style={{width:"10%"}}>Date</th>
                      <th style={{width:"10%"}}>Status</th>
                      <th style={{width:"5%"}}>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {expenses.map((exp, index) =>(
                      <tr key={exp.id}>
                  <td>{index + 1}</td>
                  <td>{exp.id}</td>
                  <td>{exp.employee.full_name}</td>
                  <td>{exp.category}</td>
                  <td>{exp.description}</td>
                  <td>₹{exp.amount}</td>
                  <td>
                    <a href={getInvoiceUrl(exp.invoice)} target='_blank' rel='noopener noreferrer'>View</a>
                  </td>
                  <td>{new Date(exp.date).toLocaleDateString()}</td>
                  <td>{exp.status}</td>
                  <td>
                    <FaCheck className='approve-btn' onClick={() => handleStatusUpdate(exp.id, "APPROVED")} />
                    <FaTimes className='reject-btn' onClick={() => handleStatusUpdate(exp.id, "REJECTED")} />
                  </td>
                      </tr>
                    ))}             
                  </tbody>
                </table>
              </div>     
      </>
    )
}

export default AdminExpenseRequests
