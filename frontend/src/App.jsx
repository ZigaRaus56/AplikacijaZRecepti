import { BrowserRouter, Link } from "react-router-dom";
import Routing from "./components/routing/Routing";

export default function App() {
  return (
    <BrowserRouter>
      <h1>Recepti</h1>
      <nav>
        <Link to="/">Seznam</Link> | <Link to="/recepti/nov">Nov recept</Link>
      </nav>
      <Routing />
    </BrowserRouter>
  );
}