import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Layout } from 'antd';
import SidebarMenu from './components/SidebarMenu';// Adjust the import path
import ListCustomersComponent from './components/ListCustomersComponent'; // Adjust the import path
import RegisterationComponent from './components/RegistrationComponent'; // Adjust the import path
import SearchComponent from './components/SearchComponent'; // Adjust the import path

/**
 * The main App component of the application.
 * 
 * This component serves as the root component and is responsible for rendering
 * the ListCustomersComponent, which displays a list of customers.
 * 
 * @returns {JSX.Element} The JSX code to render the App component.
 */
const { Content, Header } = Layout;

function App() {
  return (
    <Router>
      <Layout style={{ minHeight: '100vh' }}>
        <SidebarMenu />
        <Layout>
          <Header style={{ background: '#fff', padding: 0 }}>
            <h2 className='text-center'>PASCO JOB POC</h2>
          </Header>
          <Content style={{ margin: '0 16px' }}>
            <Routes>
              <Route path="/search" element={<SearchComponent />} />
              <Route path="/customers" element={<ListCustomersComponent />} />
              <Route path="/register" element={<RegisterationComponent />} />
            </Routes>
          </Content>
        </Layout>
      </Layout>
    </Router>
  );
}

export default App;