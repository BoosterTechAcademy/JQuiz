import { useEffect, useRef, useState } from "react";
import { questions as rawQuestions } from "../data/questions";
import "./quiz.css";

const shuffle = (arr) => [...arr].sort(() => Math.random() - 0.5);

export default function Quiz() {
  const [started, setStarted] = useState(false);
  const [finished, setFinished] = useState(false);
  const [questions, setQuestions] = useState([]);
  const [index, setIndex] = useState(0);
  const [answers, setAnswers] = useState({});
  const [time, setTime] = useState(300); // 5 minutes

  const quizRef = useRef(null);
  const startBtnRef = useRef(null);

  /* ================= START QUIZ ================= */
  const startQuiz = () => {
    setQuestions(shuffle(rawQuestions));
    setIndex(0);
    setAnswers({});
    setTime(300);
    setStarted(true);
    setFinished(false);

    document.documentElement.requestFullscreen?.();
  };

  /* ================= SUBMIT QUIZ (ONLY EXIT) ================= */
  const submitQuiz = () => {
    setStarted(false);
    setFinished(true);

    document.exitFullscreen?.();
    startBtnRef.current?.focus();
  };

  /* ================= TIMER ================= */
  useEffect(() => {
    if (!started) return;

    const timer = setInterval(() => {
      setTime((t) => {
        if (t <= 1) {
          submitQuiz();
          return 0;
        }
        return t - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [started]);

  /* ================= FORCE FOCUS ================= */
  useEffect(() => {
    if (!started) return;
    quizRef.current?.focus();
  }, [started, index]);

  /* ================= HARD KEYBOARD LOCK ================= */
  useEffect(() => {
    if (!started) return;

    const blockKeys = (e) => {
      // Block ESC completely
      if (e.key === "Escape") {
        e.preventDefault();
        e.stopPropagation();
        return;
      }

      // Block Tab navigation
      if (e.key === "Tab") {
        e.preventDefault();
        return;
      }

      // Block Alt / Ctrl / Meta shortcuts
      if (e.altKey || e.ctrlKey || e.metaKey) {
        e.preventDefault();
        return;
      }
    };

    document.addEventListener("keydown", blockKeys, true);
    return () =>
      document.removeEventListener("keydown", blockKeys, true);
  }, [started]);

  /* ================= BLOCK COPY / RIGHT CLICK ================= */
  useEffect(() => {
    if (!started) return;

    const block = (e) => e.preventDefault();

    document.addEventListener("copy", block);
    document.addEventListener("cut", block);
    document.addEventListener("paste", block);
    document.addEventListener("contextmenu", block);

    return () => {
      document.removeEventListener("copy", block);
      document.removeEventListener("cut", block);
      document.removeEventListener("paste", block);
      document.removeEventListener("contextmenu", block);
    };
  }, [started]);

  /* ================= AUTO SUBMIT ON TAB SWITCH ================= */
  useEffect(() => {
    if (!started) return;

    const handleVisibility = () => {
      if (document.hidden) {
        submitQuiz();
      }
    };

    document.addEventListener("visibilitychange", handleVisibility);
    return () =>
      document.removeEventListener("visibilitychange", handleVisibility);
  }, [started]);

  /* ================= FORCE FOCUS BACK ON CLICK ================= */
  useEffect(() => {
    if (!started) return;

    const refocus = () => {
      quizRef.current?.focus();
    };

    window.addEventListener("blur", refocus);
    document.addEventListener("mousedown", refocus);

    return () => {
      window.removeEventListener("blur", refocus);
      document.removeEventListener("mousedown", refocus);
    };
  }, [started]);

  /* ================= SCORE ================= */
  const score = questions.reduce(
    (s, q, i) => s + (answers[i] === q.answer ? 1 : 0),
    0
  );

  return (
    <div className="app">
      {/* ================= START SCREEN ================= */}
      <div className={started ? "blur" : ""}>
        <h1>React Quiz</h1>
        <p>10 Questions · 5 Minutes</p>

        {!started && !finished && (
          <button ref={startBtnRef} onClick={startQuiz}>
            Start Quiz
          </button>
        )}
      </div>

      {/* ================= QUIZ ================= */}
      {started && (
        <div className="overlay">
          <div
            className="quiz"
            ref={quizRef}
            tabIndex={-1}
            role="dialog"
            aria-modal="true"
          >
            <div className="header">
              <span>
                Q {index + 1}/{questions.length}
              </span>
              <span>
                ⏱ {Math.floor(time / 60)}:
                {String(time % 60).padStart(2, "0")}
              </span>
            </div>

            <h2>{questions[index].q}</h2>

            {questions[index].options.map((opt, i) => (
              <label key={i} className="option">
                <input
                  type="radio"
                  checked={answers[index] === i}
                  onChange={() =>
                    setAnswers({ ...answers, [index]: i })
                  }
                />
                {opt}
              </label>
            ))}

            <div className="actions">
              {index > 0 && (
                <button onClick={() => setIndex(index - 1)}>
                  Previous
                </button>
              )}

              {index < questions.length - 1 ? (
                <button onClick={() => setIndex(index + 1)}>
                  Next
                </button>
              ) : (
                <button onClick={submitQuiz}>Submit</button>
              )}
            </div>
          </div>
        </div>
      )}

      {/* ================= RESULT ================= */}
      {finished && (
        <div className="result">
          <h2>Result</h2>
          <p>
            Score: {score}/{questions.length} (
            {Math.round((score / questions.length) * 100)}%)
          </p>

          <button onClick={startQuiz}>Restart Quiz</button>
        </div>
      )}
    </div>
  );
}
