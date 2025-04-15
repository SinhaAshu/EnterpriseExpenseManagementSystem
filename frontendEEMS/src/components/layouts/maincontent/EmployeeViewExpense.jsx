import React from 'react'
import '@/components/styles/homelayout.css'
import { FaEdit, FaTrash } from 'react-icons/fa'    

const EmployeeViewExpense = () => {
  return (
    <>
    <h2 className='emp-expenselist-heading'>Your Expense List</h2>
        <div className='emp-expenselist'>
          <table className='expenselist-table'>
            <thead>
              <tr>
                <th style={{width:"5%"}}>#ID</th>
                <th style={{width:"10%"}}>Category</th>
                <th style={{width:"45%"}}>Description</th>
                <th style={{width:"10%"}}>Amount</th>
                <th style={{width:"10%"}}>Invoice</th>
                <th style={{width:"10%"}}>Date</th>
                <th style={{width:"10%"}}>Action</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Travel</td>
                <td>Flight to Paris for an important meeting</td>
                <td>1000</td>
                <td><a href="#">View</a></td>
                <td>19-03-2025</td>
                <td>
                    <FaEdit className='edit-btn'/>
                    <FaTrash className='delete-btn'/>
                </td>
              </tr>             
            </tbody>
          </table>
        </div>
    </>
  )
}

export default EmployeeViewExpense
