package dev.shulika.podologia.service;

public interface CustomSecurityExpression {
    boolean canAccessUser(Long id);
}
