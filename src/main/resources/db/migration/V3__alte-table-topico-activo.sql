ALTER TABLE topico ADD COLUMN activo BOOLEAN;
UPDATE topico SET activo = TRUE;
ALTER TABLE topico ALTER COLUMN activo SET NOT NULL;