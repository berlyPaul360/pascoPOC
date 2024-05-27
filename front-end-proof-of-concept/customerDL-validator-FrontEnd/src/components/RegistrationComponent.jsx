import React, { useState } from 'react';
import { Form, Input, Button, Layout, message } from 'antd';
import axios from 'axios';
import { addCustomer,verifyDriverLicense } from '../services/CustomerDetailsService';

const { Content } = Layout;

export const RegistrationComponent = () => {
  const [form] = Form.useForm();
  const [dlValid, setDlValid] = useState(false);

  

  const handleValidateDL = async () => {
    try {
      const driverLicense = form.getFieldValue('DL');
      const dobDate = form.getFieldValue('dobDate');
      const response = await verifyDriverLicense(driverLicense, dobDate);
      setDlValid(response.data.valid);
      message.success(response.data.valid ? 'Driver License is valid' : 'Driver License is invalid');
    } catch (error) {
      message.error('Failed to validate Driver License');
      console.error(error);
    }
  };

  const handleSubmit = async (values) => {
    try {
      await addCustomer(values);
      console.log(values);
      message.success('Customer registered successfully!');
      form.resetFields();
    } catch (error) {
      message.error('Failed to register customer!');
      console.error(error);
    }
  };


  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Content style={{ margin: '0 16px' }}>
        <h2 className="text-center">Register Customer (PASCO proof of concept)</h2>
        <Form
          form={form}
          layout="vertical"
          onFinish={handleSubmit}
          style={{ maxWidth: 600, margin: '0 auto' }}
        >
          <Form.Item
            name="firstName"
            label="First Name"
            rules={[{ required: true, message: 'Please input your first name!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="lastName"
            label="Last Name"
            rules={[{ required: true, message: 'Please input your last name!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="address"
            label="Address"
            rules={[{ required: true, message: 'Please input your address!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="city"
            label="City"
            rules={[{ required: true, message: 'Please input your city!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="state"
            label="State"
            rules={[{ required: true, message: 'Please input your state!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="zipCode"
            label="Zip Code"
            rules={[{ required: true, message: 'Please input your zip code!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="dobDate"
            label="Date of Birth"
            rules={[{ required: true, message: 'Please input your date of birth!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="dl"
            label="Driver License"
            rules={[{ required: true, message: 'Please input your driver license!' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="DLValid"
            label="Driver License Valid"
          >
            <Input value={dlValid ? 'Yes' : 'No'} disabled />
          </Form.Item>
          <Form.Item>
            <Button type="primary" onClick={handleValidateDL}>Validate Driver License</Button>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">Save</Button>
          </Form.Item>
        </Form>
      </Content>
    </Layout>
  );
};

export default RegistrationComponent;
