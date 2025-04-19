import React, { useState } from 'react';
import axios from 'axios';
import '@/components/styles/editexpensemodal.css';

const EditExpenseModal = ({ expense, onClose, onSave }) => {
  const [formData, setFormData] = useState({
    category: expense.category,
    description: expense.description,
    amount: expense.amount,
    invoice: null, 
  });

  const token = localStorage.getItem('token');

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'invoice') {
      setFormData(prev => ({ ...prev, invoice: files[0] }));
    } else {
      setFormData(prev => ({ ...prev, [name]: value }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updatedData = new FormData();
      updatedData.append('category', formData.category);
      updatedData.append('description', formData.description);
      updatedData.append('amount', formData.amount);
      if (formData.invoice) {
        updatedData.append('invoice', formData.invoice);
      }

      const response = await axios.put(
        `http://localhost:8080/api/employee/update-expense/${expense.id}`,
        updatedData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'multipart/form-data',
          },
        }
      );

      alert('Expense updated!');
      onSave(response.data);
    } catch (err) {
      alert('Error updating expense.');
      console.error(err);
    }
  };

  return (
    <div className="modal-backdrop">
      <div className="modal-content">
        <h3>Edit Expense</h3>
        <form onSubmit={handleSubmit}>

          <label> Category: 
            <select name='category' value={formData.category} onChange={handleChange} required>
                <option>Travel</option>
                <option>Food</option>
                <option>Accomodation</option>
                <option>Others</option>
            </select>  
          </label>

          <label> Description: <textarea name="description" value={formData.description} onChange={handleChange} required />
          </label>

          <label> Amount:<input type="number" name="amount" value={formData.amount} onChange={handleChange} required />
          </label>

          <label> Invoice:
            <input type="file" name="invoice" accept="application/pdf,image/*"onChange={handleChange}/>
          </label>

          <div className="modal-actions">
            <button type="submit"className='addexpense-submit'>Save</button>
            <button type="button" onClick={onClose} className="cancel-btn"> Cancel </button>
          </div>

        </form>
      </div>
    </div>
  );
};

export default EditExpenseModal;
