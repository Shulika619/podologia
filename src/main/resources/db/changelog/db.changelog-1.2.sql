--liquibase formatted sql

--changeset shulika:1
ALTER TABLE price
    ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT true;