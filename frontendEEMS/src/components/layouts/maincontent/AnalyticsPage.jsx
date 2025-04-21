import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  LineChart, Line, BarChart, Bar,
  XAxis, YAxis, CartesianGrid, Tooltip,
  Legend, ResponsiveContainer,
} from 'recharts';
import '@/components/styles/analyticspage.css';
import { toast } from 'react-toastify';

const AnalyticsPage = () => {
  const [monthlyData, setMonthlyData] = useState([]);
  const [categoryData, setCategoryData] = useState([]);
  const [filterYear, setFilterYear] = useState(new Date().getFullYear());
  const [filterType, setFilterType] = useState('monthly');

  const token = localStorage.getItem('token');

  useEffect(() => {
    fetchTrendData();
    fetchCategoryData();
  }, [filterYear, filterType]);

  const fetchTrendData = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/analytics/${filterType}?year=${filterYear}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setMonthlyData(res.data);
    } catch (err) {
      toast.error('Failed to load trend analytics');
    }
  };

  const fetchCategoryData = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/analytics/category?year=${filterYear}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setCategoryData(res.data);
    } catch (err) {
      toast.error('Failed to load category breakdown');
    }
  };

  const downloadReport = async (format) => {
    try {
      const res = await axios.get(`http://localhost:8080/api/analytics/export/${format}?year=${filterYear}`, {
        headers: {
          Authorization: `Bearer ${token}`
        },
        responseType: 'blob'
      });

      const url = window.URL.createObjectURL(new Blob([res.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `expense_report.${format === 'pdf' ? 'pdf' : 'xlsx'}`);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (err) {
      toast.error('Download failed!');
    }
  };

  return (
    <div className="analytics-container">
      <h2>📊 Expense Analytics Dashboard</h2>

      <div className="filters">
        <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
          <option value="monthly">Monthly Trends</option>
          <option value="yearly">Yearly Trends</option>
        </select>

        <input
          type="number"
          min="2000"
          max="2100"
          placeholder="Enter Year"
          value={filterYear}
          onChange={(e) => setFilterYear(e.target.value)}
        />

        <button className="export-btn" onClick={() => downloadReport('excel')}>⬇️ Excel</button>
        <button className="export-btn" onClick={() => downloadReport('pdf')}>⬇️ PDF</button>
      </div>

      <div className="chart-wrapper">
        <h3>📈 {filterType === 'monthly' ? 'Monthly' : 'Yearly'} Expense Trends</h3>
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={monthlyData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="label" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey="total" stroke="#8884d8" />
          </LineChart>
        </ResponsiveContainer>
      </div>

      <div className="chart-wrapper">
        <h3>📊 Category-wise Breakdown</h3>
        <ResponsiveContainer width="100%" height={300}>
          <BarChart data={categoryData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="label" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Bar dataKey="total" fill="#82ca9d" />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default AnalyticsPage;
