import { useEffect, useState } from 'react';
import { getOrdersByUser } from '../../../service/orderService';
import { useUsuarioStore } from '../../../store/useUsuarioStore';
import { getAdressByUser } from '../../../service/adressService';

export const MisPedidos = () => {
    const usuario = useUsuarioStore(state => state.usuario);
    const [pedidos, setPedidos] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [direcciones, setDirecciones] = useState<any[]>([]);

    const fetchPedidos = async () => {
        console.log('Usuario actual:', usuario);
        if (!usuario?.id) {
            console.log('No hay usuario o ID');
            return;
        }
        try {
            // Primero obtenemos las direcciones del usuario
            console.log('Obteniendo direcciones del usuario:', usuario.id);
            const direccionesData = await getAdressByUser(usuario.id);
            console.log('Direcciones obtenidas:', direccionesData);
            setDirecciones(direccionesData);

            console.log('Intentando obtener pedidos para usuario:', usuario.id);
            const data = await getOrdersByUser(usuario.id);
            console.log('Pedidos obtenidos:', data);
            setPedidos(data);
        } catch (error) {
            console.error('Error al obtener pedidos:', error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        console.log('Efecto ejecutado, usuario:', usuario);
        fetchPedidos();
        // Actualizar los pedidos cada 5 segundos
        const interval = setInterval(fetchPedidos, 5000);
        return () => clearInterval(interval);
    }, [usuario]);

    if (loading) return <div>Cargando pedidos...</div>;

    return (
        <div style={{ maxWidth: 800, margin: '0 auto' }}>
            <h2>Mis Pedidos</h2>
            {direcciones.length === 0 ? (
                <p>No tienes direcciones registradas. Por favor, agrega una dirección para poder realizar pedidos.</p>
            ) : pedidos.length === 0 ? (
                <p>No tienes pedidos realizados.</p>
            ) : (
                <ul>
                    {pedidos.map((pedido) => (
                        <li key={pedido.id} style={{ marginBottom: 20, border: '1px solid #ccc', padding: 10 }}>
                            <div><strong>Fecha:</strong> {pedido.date}</div>
                            <div><strong>Estado:</strong> {pedido.orderStatus}</div>
                            <div><strong>Total:</strong> ${pedido.summary}</div>
                            <div>
                                <strong>Productos:</strong>
                                <ul>
                                    {pedido.orderDetails?.map((detalle: any) => (
                                        <li key={detalle.id.detailId}>
                                            {detalle.detailId.productId?.name || 'Producto'} x{detalle.quantity} - ${detalle.unitPrice}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};