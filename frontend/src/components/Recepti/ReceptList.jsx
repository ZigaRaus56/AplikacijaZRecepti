import { useEffect, useState } from "react";
import { Link} from "react-router-dom";
import { getRecepti, deleteRecept } from "../../services/ReceptService";

export default function ReceptList(){
    const [recepti, setRecepti] = useState([]);

    useEffect(() => {
        getRecepti().then(setRecepti);
    }, []);

    function izbrisi(id){
        deleteRecept(id).then(() => setRecepti(recepti.filter((r) => r.id !== id)));
    }

    return (
        <ul>
            {recepti.map((r) => (
                <li key={r.id}>
                    <Link to= {`/recepti/${r.id}`}>{r.naslov}</Link>{" "}
                    <Link to= {`/recepti/${r.id}/uredi`}>Uredi</Link>{" "}
                    <button onClick={() => izbrisi(r.id)}>izbrisi</button>
                </li>
            ))}
        </ul>
    );
}

