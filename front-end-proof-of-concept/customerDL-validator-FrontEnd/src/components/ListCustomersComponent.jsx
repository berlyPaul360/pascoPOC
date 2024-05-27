import React, { useState, useEffect } from 'react';
import { Table, Button,message } from 'antd';
import { listCustomers,deleteCustomer } from '../services/CustomerDetailsService';

export const ListCustomersComponent = () => {
  const [customer, setCustomer] = useState([]);

  useEffect(() => {fetchCustomers()
    }, []);

const fetchCustomers = async () => {
    try {
        const response = await listCustomers();
        console.log("Fetched customers:", response.data);
        setCustomer(response.data);
    } catch (error) {
        console.error("Failed to fetch customers:", error);
    }
    };


  const handleEdit = (id) => {
    // Implement edit functionality
  };

  const handleDelete = async (id) => {
    try {
      await deleteCustomer(id);
      message.success('Customer deleted successfully!');
      fetchCustomers(); // Refresh the list of customers
      
    } catch (error) {
      message.error('Failed to delete customer!');
      console.error("Failed to delete customer:", error);
    }
  };

  const columns = [
    {
      title: 'First Name',
      dataIndex: 'firstName',
      key: 'firstName',
    },
    {
      title: 'Last Name',
      dataIndex: 'lastName',
      key: 'lastName',
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'City',
      dataIndex: 'city',
      key: 'city',
    },
    {
      title: 'State',
      dataIndex: 'state',
      key: 'state',
    },
    {
      title: 'Zip Code',
      dataIndex: 'zipCode',
      key: 'zipCode',
    },
    {
      title: 'Date of Birth',
      dataIndex: 'dobDate',
      key: 'dobDate',
    },
    {
      title: 'Driver License',
      dataIndex: 'dl',
      key: 'dl',
    },
    {
      title: 'Valid',
      dataIndex: 'dlvalid',
      key: 'dlvalid',
      render: (dlvalid) => (dlvalid ? 'Yes' : 'No'),
    },
    {
      title: 'Actions',
      key: 'actions',
      render: (text, record) => (
        <div>
          <Button type="primary" size="small" onClick={() => handleEdit(record.id)}>
            Edit
          </Button>
          <Button type="danger" size="small" onClick={() => {
            console.log("record id:",record.customerId)
            handleDelete(record.customerId)
          }
            } style={{ marginLeft: 8 }}>
            Delete
          </Button>
        </div>
      ),
    },
  ];

  return (
    <div style={{ padding: 24 }}>
      <h2 className='text-center'>List of Customers (PASCO JOB POC)</h2>
      <Table
        columns={columns}
        dataSource={customer}
        rowKey="customerId"
        bordered
        pagination={false}
        style={{ marginTop: 16 }}
      />
    </div>
  );
};

export default ListCustomersComponent;
