import React from 'react'
import '@/components/styles/homelayout.css'
import { FaCheck, FaTimes } from 'react-icons/fa'

const ManagerViewExpense = () => {
  return (
    <>
    <h2 className='emp-expenselist-heading'>Pending Requests</h2>
            <div className='emp-expenselist'>
              <table className='expenselist-table'>
                <thead>
                  <tr>
                    <th style={{width:"5%"}}>#UserID</th>
                    <th style={{width:"15%"}}>Name</th>
                    <th style={{width:"10%"}}>Category</th>
                    <th style={{width:"30%"}}>Description</th>
                    <th style={{width:"10%"}}>Amount</th>
                    <th style={{width:"10%"}}>Invoice</th>
                    <th style={{width:"10%"}}>Date</th>
                    <th style={{width:"10%"}}>Status</th>
                    <th style={{width:"10%"}}>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>John Mathew</td>
                    <td>Travel</td>
                    <td>Flight to Paris for an important meeting</td>
                    <td>1000</td>
                    <td><a href="#">View</a></td>
                    <td>19-03-2025</td>
                    <td>PENDING</td>
                    <td>
                        <FaCheck className='approve-btn'/>
                        <FaTimes className='reject-btn'/>
                    </td>
                  </tr>             
                </tbody>
              </table>
            </div>     
    </>
  )
}

export default ManagerViewExpense
