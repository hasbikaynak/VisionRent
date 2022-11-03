	INSERT INTO public.t_role(
	version, type)
	VALUES ( 0, 'ROLE_CUSTOMER');
	
	INSERT INTO public.t_role(
	version, type)
	VALUES ( 0, 'ROLE_ADMIN');
	
	UPDATE public.t_user_role
	SET role_id=2
	WHERE user_id=1;