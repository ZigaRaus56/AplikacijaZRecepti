import api from "./api";

export const getRecepti = (filtri = {}) => {
  const params = Object.fromEntries(
    Object.entries(filtri).filter(([, v]) => v !== "" && v != null)
  );
  return api.get("/recepti", { params }).then((r) => r.data);
};
export const getRecept = (id) => api.get(`/recepti/${id}`).then((r)=> r.data);
export const createRecept = (recept) => api.post("/recepti", recept).then((r) => r.data);
export const updateRecept = (id, recept) => api.put(`/recepti/${id}`, recept).then((r) => r.data);
export const deleteRecept = (id) => api.delete(`/recepti/${id}`);

export const getKategorije = () => api.get("/kategorije").then((r) => r.data);