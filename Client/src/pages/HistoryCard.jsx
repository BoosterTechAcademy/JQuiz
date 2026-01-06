import React from "react";

const HistoryCard = () => {
  // Temporary static data (later replace with backend API)
  const history = [
    { id: 1, date: "01 Jan 2026", score: 16 },
    { id: 2, date: "28 Dec 2025", score: 14 },
    { id: 3, date: "20 Dec 2025", score: 18 }
  ];

  return (
    <div className="card">
      <h2>🕒 Past Quiz Records</h2>

      {history.map(record => (
        <div key={record.id} className="history-item">
          <span>{record.date}</span>
          <span className="score">{record.score} / 20</span>
        </div>
      ))}
    </div>
  );
};

export default HistoryCard;
