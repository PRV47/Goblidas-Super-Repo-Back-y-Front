import api from '../components/api/axios';

export const getOrdersByUser = async (userId: number) => {
    try {
        console.log('Token en localStorage:', localStorage.getItem('token'));
        console.log('Realizando petición a /order/user/' + userId);
        const response = await api.get(`/order/user/${userId}`);
        console.log('Respuesta del servidor:', response);
        return response.data;
    } catch (error: any) {
        console.error('Error en getOrdersByUser:', error.response || error);
        throw error;
    }
};