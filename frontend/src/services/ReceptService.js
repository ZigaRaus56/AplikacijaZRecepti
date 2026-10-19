import api from "./api";

export const getRecepti = () => api.get("/recepti").then((r) => r.data);
export const getRecept = (id) => api.get(`/recepti/${id}`).then((r)=> r.data);
export const createRecept = (recept) => api.post("/recepti", recept).then((r) => r.data);
export const updateRecept = (id, recept) => api.put(`/recepti/${id}`, recept).then((r) => r.data);
export const deleteRecept = (id) => api.delete(`/recepti/${id}`);

export const getKategorije = () => api.get("/kategorije").then((r) => r.data);