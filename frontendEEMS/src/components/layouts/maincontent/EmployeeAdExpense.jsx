import React from 'react'
import '@/components/styles/homelayout.css'

const EmployeeAdExpense = () => {
  return (
    <>
       <h2 className='emp-addexpense-heading'>Add Your Expenses</h2>
      <div className='emp-addexpense'>
        <form className="addexpense-form" action="">            
            <label>Category:</label>
            <select>
                <option>Travel</option>
                <option>Food</option>
                <option>Accomodation</option>
                <option>Others</option>
            </select>
                
            <label>Description:</label>    
            <input type="text"></input>

            <label>Amount:</label>
            <input type="number"></input>

            <label>Invoice(Pdf/Image):</label>
            <input type="file" accept=".jpg,.png,.pdf"></input>

            <button className='addexpense-submit' type="submit">Save</button>   

        </form>
      </div>
    </>
  )
}

export default EmployeeAdExpense
