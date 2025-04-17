import React, { useEffect, useState } from 'react'
import '@/components/styles/homelayout.css'
import axios from 'axios';

const EmployeeExpenseStatus = () => {

  const [expenses, setExpenses] = useState([]);

  const token = localStorage.getItem('token');

  useEffect(() => {
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
    fetchExpenses();
  }, []);

  return (
    <>
    <h2 className='emp-expensestatus-heading'>Expenses Status</h2>
    <div className='emp-expensestatus'>
       <table className='expensestatus-table'>
        <thead>
            <tr>
                <th style={{width:"10%"}}>S.No.</th>
                <th style={{width:"10%"}}>#ExpenseID</th>
                <th style={{width:"20%"}}>Category</th>
                <th style={{width:"20%"}}>Amount</th>
                <th style={{width:"20%"}}>Status</th>
            </tr>
        </thead>
        <tbody>
          {expenses.map((exp, index) => (
            <tr key={exp.id}>
              <td>{index + 1}</td>
            <td>{exp.id}</td>
            <td>{exp.category}</td>
            <td>{exp.amount}</td>
            <td>{exp.status}</td>
            </tr>
          ))}
        </tbody>
       </table>
    </div>
      
    </>
  )
}

export default EmployeeExpenseStatus
