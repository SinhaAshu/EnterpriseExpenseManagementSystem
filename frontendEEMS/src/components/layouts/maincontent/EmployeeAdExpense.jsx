import React, { useState, useRef } from 'react';
import '@/components/styles/homelayout.css';
import { istokenExpired } from '../../../utils/auth';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const EmployeeAdExpense = () => {
  const navigate = useNavigate();
  const fileInputRef = useRef(null);

  const [formData, setFormData] = useState({
    category: 'Travel',
    description: '',
    amount: '',
    invoice: null
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'invoice') {
      setFormData({ ...formData, invoice: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handlesubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem('token');
    if (!token || istokenExpired(token)) {
      alert('Please log in again!');
      navigate('/');
      return;
    }

    try {
      const data = new FormData();
      data.append('category', formData.category);
      data.append('description', formData.description);
      data.append('amount', formData.amount);
      data.append('invoice', formData.invoice);

      const response = await axios.post('http://localhost:8080/api/employee/addExpense', data, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data'
        }
      });

      if (response.data) {
        alert('Expense added!');
        setFormData({
          category: 'Travel',
          description: '',
          amount: '',
          invoice: null
        });

        // Reset the file input manually
        if (fileInputRef.current) {
          fileInputRef.current.value = '';
        }
      } else {
        alert('No response from server.');
      }
    } catch (error) {
      console.error('Error:', error);
      alert(`Expense was not added: ${error.message}`);
    }
  };

  return (
    <>
      <h2 className="emp-addexpense-heading">Add Your Expenses</h2>
      <div className="emp-addexpense">
        <form className="addexpense-form" onSubmit={handlesubmit}>
          <label>Category:</label>
          <select name="category" value={formData.category} onChange={handleChange} required>
            <option>Travel</option>
            <option>Food</option>
            <option>Accomodation</option>
            <option>Others</option>
          </select>

          <label>Description:</label>
          <input
            type="text"
            name="description"
            value={formData.description}
            onChange={handleChange}
            required
          />

          <label>Amount:</label>
          <input
            type="number"
            name="amount"
            value={formData.amount}
            onChange={handleChange}
            required
          />

          <label>Invoice (PDF/Image):</label>
          <input
            type="file"
            accept=".jpg,.png,.pdf"
            name="invoice"
            onChange={handleChange}
            ref={fileInputRef}
          />

          <button className="addexpense-submit" type="submit">
            Save
          </button>
        </form>
      </div>
    </>
  );
};

export default EmployeeAdExpense;
