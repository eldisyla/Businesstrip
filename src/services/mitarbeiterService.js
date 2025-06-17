const BASE_URL = "http://localhost:8080/api/mitarbeiter";

export async function getMitarbeiter() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function createMitarbeiter(mitarbeiter) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(mitarbeiter),
  });
  return res.json();
}

export async function deleteMitarbeiter(id) {
  await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
}
