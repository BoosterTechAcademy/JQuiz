import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/axios";
import "./Signup.css";

export default function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

    const navigate = useNavigate();
const signup = async () => {
  setLoading(true);

  try {
    const res = await api.post("/api/auth/signup", {
      name,
      email,
      password,
    });

    localStorage.setItem("token", res.data.token);
    navigate("/dashboard");
  } catch (error) {
    console.error(error);
    alert(
      error.response?.data?.message || "Signup failed"
    );
  } finally {
    setLoading(false);
  }
};

const handleSubmit = (e) => {
  e.preventDefault();
  signup();
};


  return (
    <div className="signup-container">
      <div className="signup-card">
        <div className="signup-header">
          <div className="logo-circle">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
              <circle cx="9" cy="7" r="4" />
              <line x1="19" y1="8" x2="19" y2="14" />
              <line x1="22" y1="11" x2="16" y2="11" />
            </svg>
          </div>
          <h2>Create Account</h2>
          <p>Join us and start your journey today</p>
        </div>

        <div className="signup-form">
          <div className="input-group">
            <label htmlFor="name">Full Name</label>
            <input
              id="name"
              type="text"
              placeholder="Enter your full name"
              value={name}
              onChange={e => setName(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label htmlFor="email">Email Address</label>
            <input
              id="email"
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              type="password"
              placeholder="Create a strong password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />
            <span className="password-hint">Must be at least 8 characters</span>
          </div>

          <button onClick={handleSubmit} className="btn-primary" disabled={loading}>
            {loading ? "Creating Account..." : "Create Account"}
          </button>
        </div>

        <div className="signup-footer">
          <p>Already have an account? <a href="/">Sign in</a></p>
        </div>

        <div className="terms-text">
          By signing up, you agree to our <a href="/terms">Terms of Service</a> and <a href="/privacy">Privacy Policy</a>
        </div>
      </div>
    </div>
  );
}