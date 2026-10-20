import { Routes, Route } from "react-router-dom";
import ReceptList from "../Recepti/ReceptList";
import ReceptDetail from "../Recepti/ReceptDetail";
import ReceptForm from "../Recepti/ReceptForm";

export default function Routing() {
  return (
    <Routes>
      <Route path="/" element={<ReceptList />} />
      <Route path="/recepti/nov" element={<ReceptForm />} />
      <Route path="/recepti/:id" element={<ReceptDetail />} />
      <Route path="/recepti/:id/uredi" element={<ReceptForm />} />
    </Routes>
  );
}