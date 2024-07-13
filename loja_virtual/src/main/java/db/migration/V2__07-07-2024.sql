select constraint_name 
      from information_schema.constraint_column_usage  
      where table_name='usuario_acesso' 
      and constraint_name <>'unique_acesso_user';
      
  --deletando contraint
  alter TABLE usuario_acesso drop constraint "uk_fhwpg5wu1u5p306q8gycxn9ky" ;