import React from 'react'
import { FaTrash } from 'react-icons/fa'
import '@/components/styles/listdesign.css'

const ListofManger = () => {
  return (
    <>
    <h2 style={{textAlign:"center"}}>List of Managers</h2>
    <div className='list-container'>
      <table className='list-table'>
        <thead className='list-table-head'>
          <tr>
            <th >#ID</th>
            <th >Manager's Name</th>
            <th >Manager's Email</th>
            <th >Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>John Doe</td>
            <td>johndun@gmail.com</td>
            <td className='delete-btn'><FaTrash /></td>
           </tr>
        </tbody>
      </table>
      </div>
    </>
  )
}

export default ListofManger
