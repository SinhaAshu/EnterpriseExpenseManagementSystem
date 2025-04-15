export const logoutUser = () => {
    ['token', 'email', 'role'].forEach(key => localStorage.removeItem(key));
  };