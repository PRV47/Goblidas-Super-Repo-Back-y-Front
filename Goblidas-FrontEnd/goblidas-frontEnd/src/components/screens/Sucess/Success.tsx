import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import { useCarritoStore } from '../../../store/useCarritoStore';

export const Success = () => {
    const navigate = useNavigate();
    const vaciarCarrito = useCarritoStore((state) => state.vaciarCarrito);

    useEffect(() => {
        vaciarCarrito();
        Swal.fire({
            title: '¡Compra exitosa!',
            text: 'Tu pedido ha sido procesado correctamente.',
            icon: 'success',
            confirmButtonText: 'Ver mis pedidos',
            confirmButtonColor: '#7bd15f'
        }).then((result) => {
            if (result.isConfirmed) {
                navigate('/mis-pedidos');
            }
        });
    }, [vaciarCarrito, navigate]);

    return null;
};