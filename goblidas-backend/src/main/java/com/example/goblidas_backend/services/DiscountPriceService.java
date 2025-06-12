package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.DiscountPrice;
import com.example.goblidas_backend.repositories.DiscountPriceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DiscountPriceService extends BaseService<DiscountPrice> {

    @Autowired
    private DiscountPriceRepository discountPriceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DiscountPriceService(JpaRepository<DiscountPrice, Long> baseRepository){
        super(baseRepository);
    }

    public List<DiscountPrice> findByPriceId(Long priceId) {
        System.out.println("Buscando descuentos para priceId: " + priceId);
        List<DiscountPrice> discounts = discountPriceRepository.findByPriceIdId(priceId);
        System.out.println("Descuentos encontrados: " + discounts.size());
        return discounts;
    }

    @Transactional
    public DiscountPrice updateOrCreateDiscountPrice(DiscountPrice discountPrice) {
        System.out.println("Iniciando updateOrCreateDiscountPrice");
        System.out.println("Datos recibidos - PriceId: " + discountPrice.getPriceId().getId() + 
                         ", DiscountId: " + discountPrice.getDiscountId().getId() + 
                         ", Active: " + discountPrice.getActive());

        try {
            // Limpiar la sesión de Hibernate
            entityManager.clear();

            // Buscar descuentos existentes para este precio
            List<DiscountPrice> existingDiscounts = discountPriceRepository.findByPriceIdId(discountPrice.getPriceId().getId());
            System.out.println("Descuentos existentes encontrados: " + existingDiscounts.size());

            // Desactivar todos los descuentos existentes excepto el que estamos actualizando
            for (DiscountPrice existingDiscount : existingDiscounts) {
                if (!existingDiscount.getDiscountId().getId().equals(discountPrice.getDiscountId().getId())) {
                    System.out.println("Desactivando descuento existente");
                    System.out.println("Descuento existente - ID: " + existingDiscount.getId() + 
                                     ", PriceId: " + existingDiscount.getPriceId().getId() + 
                                     ", DiscountId: " + existingDiscount.getDiscountId().getId());
                    
                    existingDiscount.setActive(false);
                    discountPriceRepository.save(existingDiscount);
                    System.out.println("Descuento existente desactivado exitosamente");
                }
            }

            // Buscar si existe el descuento que queremos activar
            DiscountPrice existingDiscount = existingDiscounts.stream()
                .filter(d -> d.getDiscountId().getId().equals(discountPrice.getDiscountId().getId()))
                .findFirst()
                .orElse(null);

            if (existingDiscount != null) {
                // Si existe, lo actualizamos
                System.out.println("Actualizando descuento existente");
                existingDiscount.setActive(true);
                return discountPriceRepository.save(existingDiscount);
            } else {
                // Si no existe, creamos uno nuevo
                System.out.println("Creando nuevo descuento");
                discountPrice.setActive(true);
                return discountPriceRepository.save(discountPrice);
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar/crear descuento: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
