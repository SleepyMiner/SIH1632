import { BrowserRouter, Routes, Route } from "react-router-dom";
import JobPage from "./pages/JobPage";
import HomePage from "./pages/HomePage";
import { useState } from "react";
import Navbar from "./components/Navbar";

export default function App() {
  const [activePage, setActivePage] = useState("Home");

  return (
    <div>
      <Navbar activePage={activePage} setActivePage={setActivePage} />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="jobs" element={<JobPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}
