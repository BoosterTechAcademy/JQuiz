import React from "react";
import { useNavigate } from "react-router-dom";

const QuizCard = () => {
  const navigate = useNavigate();

  return (
    <div className="card">
      <h2>🚀 Start New Quiz</h2>
      <p>Test your knowledge with a timed quiz.</p>

      <button
        className="start-btn"
        onClick={() => navigate("/quiz")}
      >
        Start Quiz
      </button>
    </div>
  );
};

export default QuizCard;
