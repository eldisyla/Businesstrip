import { useState, useEffect } from "react";

const baseUrl = import.meta.env.VITE_API_BASE_URL;

export default function useFetch(endpoint) {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const controller = new AbortController();
    async function fetchData() {
      try {
        const response = await fetch(baseUrl + endpoint, {
          signal: controller.signal,
        });
        if (!response.ok) throw await response.json();
        const json = await response.json();
        setData(json);
      } catch (e) {
        if (e.name !== "AbortError") setError(e);
      } finally {
        setLoading(false);
      }
    }
    fetchData();
    return () => controller.abort(); // Cleanup bei Komponentenwechsel
  }, [endpoint]);

  return { data, error, loading };
}
