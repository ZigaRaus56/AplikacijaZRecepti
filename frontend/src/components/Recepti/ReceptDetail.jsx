import { useEffect, useState } from "react";
import  {useParams, Link} from "react-router-dom";
import {getRecept} from "../../services/ReceptService";


export default function ReceptDetail() {
  const { id } = useParams();
  const [recept, setRecept] = useState(null);
  const [napaka, setNapaka] = useState("");

  useEffect(() => {
    getRecept(id)
      .then(setRecept)
      .catch((e) => setNapaka(e.message));
  }, [id]);

  if (napaka) return <p style={{ color: "red" }}>{napaka}</p>;
  if (!recept) return <p>Nalaganje...</p>;


      return (
        <div>
      <Link to="/">Nazaj</Link>
      <h2>{recept.naslov}</h2>
      <p>{recept.opis}</p>
      <p>Kategorija: {recept.kategorija?.ime ?? "-"}</p>
      <h3>Sestavine</h3>
      <ul>
        {(recept.sestavine ?? []).map((s) => (
          <li key={s.id}>{s.kolicina} {s.enota} {s.naziv}</li>
        ))}
      </ul>
      <h3>Navodila</h3>
      <p>{recept.navodila}</p>
    </div>
  );
}