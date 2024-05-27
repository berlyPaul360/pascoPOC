import axios from "axios";

const REST_API_BASE_URL = "http://localhost:3031/customer";

export const listCustomers = async () => {
    return await axios.get(REST_API_BASE_URL);
}

export const addCustomer = async (customer) => {
    return await axios.post(REST_API_BASE_URL, customer);
  }


  export const verifyDriverLicense = async (driverLicense, dateOfBirth) => {
    return await axios.get(`${REST_API_BASE_URL}/verifyDriverLicense`, {
      params: {
        driverLicense: driverLicense,
        dateOfBirth: dateOfBirth
      }
    });
  }


  export const deleteCustomer = async (customerId) => {
    console.log(customerId);
    return await axios.delete(`${REST_API_BASE_URL}/${customerId}`);
  }

  export const searchCustomerByDlAndDob = async (driverLicense, dateOfBirth) => {
    return await axios.get(`${REST_API_BASE_URL}/search`, {
      params: { driverLicense, dateOfBirth }
    });

    
  }