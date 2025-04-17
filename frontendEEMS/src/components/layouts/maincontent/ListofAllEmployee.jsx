import React, { useEffect, useState } from 'react'
import { FaTrash } from 'react-icons/fa'
import '@/components/styles/listdesign.css'
import axios from 'axios';
import { toast } from 'react-toastify';

const ListofAllEmployee = () => {

  const [employee, setEmployee] = useState([]);

  const token = localStorage.getItem('token');

  const fetchEmployee = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/admin/view-employee', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setEmployee(response.data);
    } catch (error) {
      console.error('Error fetching expenses:', error);
    }
  };

  useEffect(() => {
    fetchEmployee();
  }, []);

  const handleDelete = async (email) => {
    const confirmDelete = window.confirm(`Are you sure you want to delete?`);
    if (!confirmDelete) return;
  
    try {
      const response = await axios.delete(`http://localhost:8080/api/admin/delete-employee?email=${email}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      toast.success("Employee deleted successfully!");
      setEmployee(employee.filter(emp => emp.email !== email)); // update UI
    } catch (error) {
      toast.error("Failed to delete employee.");
      console.error("Delete error:", error);
    }
  };
  

  const handleChange = async (role) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/admin/view-employee/role?role=${role}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      setEmployee(response.data);
      toast.success(`Showing employees with role: ${role}`);
    } catch (error) {
      toast.error("Failed to fetch employees by role. Please try again.");
      console.error("Error fetching employees by role:", error);
    }
  };
  

  return (
    <>
    <h2 style={{textAlign:"center"}}>All Employees</h2>

    <div style={{ display: 'flex', justifyContent: 'flex-end', padding: '0 20px 10px' }}>
    <button className='findManager-btn' onClick={fetchEmployee}>View All</button>
      <button className='findManager-btn' onClick={() => handleChange("Manager")}>View Managers</button>
    </div>

    <div className='list-container'>
      <table className='list-table'>
        <thead className='list-table-head'>
          <tr>
            <th >S.No.</th>
            <th >#ID</th>
            <th >Name</th>
            <th >Email</th>
            <th >Action</th>
          </tr>
        </thead>
        <tbody>
          {employee.map((emp, index) =>(
          <tr key={emp.uid}>
            <td>{index + 1}</td>
            <td>{emp.uid}</td>
            <td>{emp.full_name}</td>
            <td>{emp.email}</td>
            <td className='delete-btn'>
              <FaTrash className='delete-btn' onClick={() => handleDelete(emp.email)}/>
            </td>
           </tr>
           ))}
        </tbody>
      </table>
      </div>
    </>
  )
}

export default ListofAllEmployee
