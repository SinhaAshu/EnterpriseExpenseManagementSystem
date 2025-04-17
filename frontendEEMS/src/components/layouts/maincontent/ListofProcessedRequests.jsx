import React, { useEffect, useState } from 'react'
import { FaTrash } from 'react-icons/fa'
import axios from 'axios';

const ListofProcessedRequests = () => {
  
  const [expenses, setExpenses] = useState([]);

  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchExpenses = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/dashboard/expense/processed-requests', {
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

  return (
    <>
    <h2 style={{textAlign:"center"}}>Processed Requests</h2>
    <div className='list-container'>
      <table className='list-table'>
        <thead>
          <tr>
            <th style={{ width: "10%" }}>S.No.</th>
            <th style={{ width: "10%" }}>#ExpenseID</th>
            <th style={{ width: "20%" }}>Name</th>
            <th style={{ width: "10%" }}>Category</th>
            <th style={{ width: "10%" }}>Invoice</th>
            <th style={{ width: "10%" }}>Status</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((exp, index) => (
            <tr key = {exp.id} >
                <td>{index + 1}</td>
                <td>{exp.id}</td>
                <td>{exp.employee.full_name}</td>
                <td>{exp.category}</td>
                <td>
                <a href={getInvoiceUrl(exp.invoice)} target='_blank' rel='noopener noreferrer'>View</a>
                </td>
                <td>{exp.status}</td>
            </tr>
             ))}
        </tbody>
      </table>
      </div>
    </>
  )
}

export default ListofProcessedRequests
