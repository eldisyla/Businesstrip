const BASE_URL = "http://localhost:8080/api/leistungen"; // Backend-Endpunkt

export async function getLeistungen() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function createLeistung(leistung) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(leistung),
  });
  return res.json();
}

export async function deleteLeistung(id) {
  await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
}
