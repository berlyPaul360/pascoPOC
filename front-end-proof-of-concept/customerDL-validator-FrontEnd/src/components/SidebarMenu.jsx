import React from 'react';
import { Menu, Layout } from 'antd';
import { useNavigate } from 'react-router-dom';

const { Sider } = Layout;

export const SidebarMenu = () => {
  const navigate = useNavigate();

  return (
    <Sider>
      <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
        <Menu.Item key="1" onClick={() => navigate('/search')}>
          Search
        </Menu.Item>
        <Menu.Item key="2" onClick={() => navigate('/customers')}>
          View All Customers
        </Menu.Item>
        <Menu.Item key="3" onClick={() => navigate('/register')}>
          Register Customer
        </Menu.Item>
      </Menu>
    </Sider>
  );
};

export default SidebarMenu;
