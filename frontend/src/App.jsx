import { useState } from 'react';
import './App.css'; // Usaremos un CSS limpio

function App() {
    const [fechaInicio, setFechaInicio] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [reporte, setReporte] = useState([]);
    const [buscado, setBuscado] = useState(false);

    const consultarReporte = async (e) => {
        e.preventDefault();
        try {
            // Llamada al backend de Spring Boot (por defecto en puerto 8080)
            const response = await fetch(`http://localhost:8080/api/logistica/reporte?inicio=${fechaInicio}&fin=${fechaFin}`);
            const data = await response.json();
            setReporte(data);
            setBuscado(true);
        } catch (error) {
            console.error("Error al conectar con la API:", error);
            alert("Error al conectar con el backend. Revisa que Spring Boot esté corriendo.");
        }
    };

    return (
        <main>
            <header>
                <h1>Panel de Logística: Costos de Envío</h1>
                <p>Calcula el costo generado por cada repartidor según su zona de entrega.</p>
            </header>

            <section>
                <form onSubmit={consultarReporte}>
                    <fieldset>
                        <legend>Filtrar por rango de fechas</legend>

                        <label htmlFor="inicio">Fecha Inicio:</label>
                        <input
                            type="date"
                            id="inicio"
                            value={fechaInicio}
                            onChange={(e) => setFechaInicio(e.target.value)}
                            required
                        />

                        <label htmlFor="fin">Fecha Fin:</label>
                        <input
                            type="date"
                            id="fin"
                            value={fechaFin}
                            onChange={(e) => setFechaFin(e.target.value)}
                            required
                        />

                        <button type="submit">Calcular Costos</button>
                    </fieldset>
                </form>
            </section>

            {buscado && (
                <section>
                    <article>
                        <h2>Resultados del período</h2>
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">Repartidor</th>
                                <th scope="col">Envíos</th>
                                <th scope="col">Total kg</th>
                                <th scope="col">Zona</th>
                                <th scope="col">Tarifa/kg</th>
                                <th scope="col">Costo Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            {reporte.map((fila) => (
                                <tr key={fila.nombreRepartidor}>
                                    <td>{fila.nombreRepartidor}</td>
                                    <td>{fila.cantidadEnvios}</td>
                                    <td>{fila.cantidadEnvios === 0 ? '—' : `${fila.totalKg} kg`}</td>
                                    <td>{fila.zonaInfo}</td>
                                    <td>{fila.tarifaInfo}</td>
                                    <td>
                                        {fila.cantidadEnvios === 0
                                            ? 'No aplica ($0.00)'
                                            : `$${fila.costoTotal.toFixed(2)}`}
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </article>
                </section>
            )}
        </main>
    );
}

export default App;