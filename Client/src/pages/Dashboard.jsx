import Navbar from "../components/Navbar/Navbar"
import React from "react";
import QuizCard from "./QuizCard";
import HistoryCard from "./HistoryCard";
import "../styles/dashboard.css";

const Dashboard = () => {
  return (
    <div className="dashboard">
      <h1 className="dashboard-title">📊 Quiz Dashboard</h1>

      <div className="dashboard-grid">
        <QuizCard />
        <HistoryCard />
      </div>
    </div>
  );
};

export default Dashboard;
