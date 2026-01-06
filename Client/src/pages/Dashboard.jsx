import Navbar from "../components/Navbar/Navbar"
import Quiz from "../components/Quiz/Quiz";
function Dashboard() {
  return (
    <div className="auth-box">
       <Navbar/>
      <h2>Dashboard</h2>
      <p>You are logged in 🎉</p>
      <Quiz/>
      
    </div>
  );
};

export default Dashboard;
