import { BrowserRouter, Link } from "react-router-dom";
import Routing from "./components/routing/Routing";

export default function App() {
  return (
    <BrowserRouter>
      <div class name="App">
        <h1>Recepti</h1>
        <nav className="nav">
          <Link to="/">Seznam</Link> | <Link to="/recepti/nov">Nov recept</Link>
        </nav>
        <Routing />
      </div>
    </BrowserRouter>
  );
}
