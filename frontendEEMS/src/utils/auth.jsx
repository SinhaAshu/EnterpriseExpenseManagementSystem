import { jwtDecode } from "jwt-decode";

export const logoutUser = () => {
    ['token', 'email', 'role'].forEach(key => localStorage.removeItem(key));
  };

  export const istokenExpired = (token) => {
    try {
      const decoded = jwtDecode(token);
      const currentTime = Date.now() / 1000;
      return decoded.exp < currentTime;
    } catch (error) {
      return true; 
    }
  };