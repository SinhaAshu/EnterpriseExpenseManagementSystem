import React from 'react'
import '@/components/styles/homelayout.css'

const EmployeeExpenseStatus = () => {
  return (
    <>
    <h2 className='emp-expensestatus-heading'>Expenses Status</h2>
    <div className='emp-expensestatus'>
       <table className='expensestatus-table'>
        <thead>
            <tr>
                <th style={{width:"10%"}}>#ID</th>
                <th style={{width:"20%"}}>Category</th>
                <th style={{width:"20%"}}>Amount</th>
                <th style={{width:"20%"}}>Status</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Travel</td>
                <td>1000</td>
                <td>PENDING</td>
            </tr>
        </tbody>
       </table>
    </div>
      
    </>
  )
}

export default EmployeeExpenseStatus
