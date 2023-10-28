import "./App.css";
import { RouterProvider } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import router from "./router";

import Home from "./components/Home";

type AuthContextType = { token: string; setToken: (token: string) => void };
export const AuthContext = createContext<AuthContextType>({
  token: "",
  setToken: () => {},
});

function App() {
  const [token, setToken] = useState(localStorage.getItem("token") as string);
  useEffect(() => {
    localStorage.getItem("token") &&
      setToken(localStorage.getItem("token") as string);
  }, []);

  useEffect(() => {
    token && localStorage.setItem("token", token);
  }, [token]);

  return (
    <AuthContext.Provider value={{ token, setToken }}>
      {!token ? <Home /> : <RouterProvider router={router} />}
    </AuthContext.Provider>
  );
}

export default App;
