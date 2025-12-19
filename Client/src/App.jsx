import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/LoginSystem/Login";
import Signup from "./components/LoginSystem/Signup";
import Dashboard from "./pages/Dashboard";
import OAuthSuccess from "./pages/OAuthSuccess";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/oauth-success" element={<OAuthSuccess />} />
      </Routes>
    </BrowserRouter>
  );
}
