const BASE_URL = "http://localhost:8080/api/kunden"; // "n" am Ende beachten

export async function getKunden() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function createKunde(kunde) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(kunde),
  });
  return res.json();
}

export async function deleteKunde(id) {
  await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
}
