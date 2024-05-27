import React, { useState } from 'react';
import { Form, Input, Button, Table, Layout, message } from 'antd';
import { searchCustomerByDlAndDob } from '../services/CustomerDetailsService';

const { Content } = Layout;

export const SearchComponent = () => {
  const [customer, setCustomer] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleSearch = async (values) => {
    try {
      setLoading(true);
      console.log('values variable:',values);
      const {dl, dobDate} = values;
      const response = await searchCustomerByDlAndDob(dl, dobDate);
      setCustomer([response.data]);
      setLoading(false);
    } catch (error) {
      message.error('Failed to fetch search results');
      console.error("Failed to fetch search results:", error);
      setLoading(false);
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
  ];

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Content style={{ margin: '0 16px' }}>
        <h2 className="text-center">Search Customers</h2>
        <Form
          layout="vertical"
          onFinish={handleSearch}
          style={{ maxWidth: 600, margin: '0 auto' }}
        >
          <Form.Item
            name="dobDate"
            label="Date of Birth"
          >
            <Input placeholder="MM-DD-YYYY" />
          </Form.Item>
          <Form.Item
            name="dl"
            label="Driver License"
          >
            <Input placeholder="Driver License" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">Search</Button>
          </Form.Item>
        </Form>
        <Table
          columns={columns}
          dataSource={customer}
          rowKey="customerId"
          loading={loading}
          bordered
          pagination={false}
          style={{ marginTop: 16 }}
        />
      </Content>
    </Layout>
  );
};

export default SearchComponent;
