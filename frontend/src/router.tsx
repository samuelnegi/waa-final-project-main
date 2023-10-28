import { createBrowserRouter } from "react-router-dom";
import Products from "./components/Products";
import Account from "./components/Account";

export default createBrowserRouter([
  { path: "", element: <Products /> },
  { path: "accounts", element: <Account /> },
]);
