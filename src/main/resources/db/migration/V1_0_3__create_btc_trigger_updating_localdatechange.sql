CREATE OR REPLACE FUNCTION update_local_date_change()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.localDateChange := CURRENT_DATE;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_local_date_change_btc
    BEFORE INSERT ON finance.btc_finance
    FOR EACH ROW
EXECUTE FUNCTION update_local_date_change();