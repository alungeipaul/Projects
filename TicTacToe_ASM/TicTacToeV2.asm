.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem biblioteci, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern exit: proc
extern malloc: proc
extern memset: proc

includelib canvas.lib
extern BeginDrawing: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
;aici declaram date

o_wins DB "O WINS", 0
x_wins DB "X WINS", 0
remiza DB "DRAW", 0
draw_string DD ?

window_title DB "Tic Tac Toe",0
area_width EQU 290
area_height EQU 350
area DD 0
values DB 41,42,43,44,45,46,47,48,49
x DD ?
y DD ?
x_values DD ?
y_values DD ?
image_width EQU 89
image_height EQU 89
symbol DD 1
end_game DD 0

counter DD 0 ; numara evenimentele de tip timer

arg1 EQU 8
arg2 EQU 12
arg3 EQU 16
arg4 EQU 20

symbol_width EQU 10
symbol_height EQU 20
include digits.inc
include letters.inc
include o.inc
include x.inc


.code
check_win proc

mov esi, offset values
mov al, byte ptr [esi]
add esi,1
mov bl, byte ptr [esi]
add esi, 1
mov cl, byte ptr [esi]

cmp al, bl
jne continue1

cmp al, cl
jne continue1

cmp bl, cl
jne continue1

jmp is_win

continue1:

mov esi, offset values
add esi, 3
mov al, byte ptr [esi]
add esi, 1
mov bl, byte ptr [esi]
add esi, 1
mov cl, byte ptr [esi]

cmp al, bl
jne continue2

cmp al, cl
jne continue2

cmp bl, cl
jne continue2

jmp is_win

continue2:
mov esi, offset values
add esi, 6
mov al, byte ptr [esi]
add esi,1
mov bl, byte ptr [esi]
add esi, 1
mov cl, byte ptr [esi]

cmp al, bl
jne continue3

cmp al, cl
jne continue3

cmp bl, cl
jne continue3

jmp is_win

continue3:
mov esi, offset values
mov al, byte ptr [esi]
add esi, 3
mov bl, byte ptr [esi]
add esi, 3
mov cl, byte ptr [esi]

cmp al, bl
jne continue4

cmp al, cl
jne continue4

cmp bl, cl
jne continue4

jmp is_win

continue4:
mov esi, offset values
add esi, 1
mov al, byte ptr [esi]
add esi, 3
mov bl, byte ptr [esi]
add esi, 3
mov cl, byte ptr [esi]

cmp al, bl
jne continue5

cmp al, cl
jne continue5

cmp bl, cl
jne continue5

jmp is_win

continue5:
mov esi, offset values
add esi, 2
mov al, byte ptr [esi]
add esi, 3
mov bl, byte ptr [esi]
add esi, 3
mov cl, byte ptr [esi]

cmp al, bl
jne continue6

cmp al, cl
jne continue6

cmp bl, cl
jne continue6

jmp is_win

continue6:
mov esi, offset values
mov al, byte ptr [esi]
add esi, 4
mov bl, byte ptr [esi]
add esi, 4
mov cl, byte ptr [esi]

cmp al, bl
jne continue7

cmp al, cl
jne continue7

cmp bl, cl
jne continue7

jmp is_win

continue7:
mov esi, offset values
add esi, 2
mov al, byte ptr [esi]
add esi, 2
mov bl, byte ptr [esi]
add esi, 2
mov cl, byte ptr [esi]

cmp al, bl
jne continue8

cmp al, cl
jne continue8

cmp bl, cl
jne continue8

jmp is_win

continue8:
is_not_win:
mov eax, 0
ret


is_win:
cmp bl, 0
je et0Wins
mov eax, offset x_wins
mov draw_string, eax

jmp skip

et0Wins:
mov eax, offset o_wins
mov draw_string, eax
mov eax, 1

skip:

ret
 
check_win endp


check_draw proc

	mov esi, offset values
	mov ecx, 9
	et_for:
		mov al, byte ptr [esi]
		cmp al, 40
		jg is_not_draw
		inc esi
	loop et_for

	mov eax, offset remiza
	mov draw_string, eax
	mov eax, 1
	ret


	is_not_draw:
	mov eax, 0
	ret

check_draw endp

;arg1 = area
;argw = x
;arg3 = y
draw_x proc

	push ebp
	mov ebp, esp
	pusha
mov edi, [ebp+arg1]
mov esi, offset culoare_x
mov ecx, image_height


first_for:
push ecx
mov eax, image_height
mov ebx, [ebp+arg2]
add eax, ebx
sub eax, ecx
mov ebx, area_width
mul ebx
shl eax, 2

mov ecx, image_width

second_for:
push eax
push ecx
push edi

mov ebx, image_width
sub ebx, ecx
mov edx, [ebp+arg3]
add ebx, edx
shl ebx, 2
add eax, ebx

add edi, eax
mov eax, dword ptr [esi]
mov dword ptr[edi], eax
add esi, 4


pop edi
pop ecx
pop eax

loop second_for

pop ecx
loop first_for

popa
	mov esp, ebp
	pop ebp
	ret
draw_x endp

;arg1 = area
;argw = x
;arg3 = y
draw_0 proc

push ebp
	mov ebp, esp
	pusha
mov edi, [ebp+arg1]
mov esi, offset culoare_0
mov ecx, image_height


first_for:
push ecx
mov eax, image_height
mov ebx, [ebp+arg2]
add eax, ebx
sub eax, ecx
mov ebx, area_width
mul ebx
shl eax, 2

mov ecx, image_width

second_for:
push eax
push ecx
push edi

mov ebx, image_width
sub ebx, ecx
mov edx, [ebp+arg3]
add ebx, edx
shl ebx, 2
add eax, ebx

add edi, eax
mov eax, dword ptr [esi]
mov dword ptr[edi], eax
add esi, 4


pop edi
pop ecx
pop eax

loop second_for

pop ecx
loop first_for

popa
	mov esp, ebp
	pop ebp
	ret
draw_0 endp


; procedura make_text afiseaza o litera sau o cifra la coordonatele date
; arg1 - simbolul de afisat (litera sau cifra)
; arg2 - pointer la vectorul de pixeli
; arg3 - pos_x
; arg4 - pos_y
make_text proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+arg1] ; citim simbolul de afisat
	cmp eax, 'A'
	jl make_digit
	cmp eax, 'Z'
	jg make_digit
	sub eax, 'A'
	lea esi, letters
	jmp draw_text
make_digit:
	cmp eax, '0'
	jl make_space
	cmp eax, '9'
	jg make_space
	sub eax, '0'
	lea esi, digits
	jmp draw_text
make_space:	
	mov eax, 26 ; de la 0 pana la 25 sunt litere, 26 e space
	lea esi, letters
	
draw_text:
	mov ebx, symbol_width
	mul ebx
	mov ebx, symbol_height
	mul ebx
	add esi, eax
	mov ecx, symbol_height
bucla_simbol_linii:
	mov edi, [ebp+arg2] ; pointer la matricea de pixeli
	mov eax, [ebp+arg4] ; pointer la coord y
	add eax, symbol_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+arg3] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, symbol_width
bucla_simbol_coloane:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb
	mov dword ptr [edi], 0
	jmp simbol_pixel_next
simbol_pixel_alb:
	mov dword ptr [edi], 0FFFFFFh
simbol_pixel_next:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane
	pop ecx
	loop bucla_simbol_linii
	popa
	mov esp, ebp
	pop ebp
	ret
make_text endp

; un macro ca sa apelam mai usor desenarea simbolului
make_text_macro macro symbol, drawArea, x, y
	push y
	push x
	push drawArea
	push symbol
	call make_text
	add esp, 16
endm

;arg1 sa fie aria , arg2 e x , arg3 e y, arg4 e dimensiunea
draw_horizontal_line proc
	push ebp
	mov ebp, esp
	pusha
	mov esi, [ebp+arg1]
	mov eax ,[ebp+arg3]
	mov ebx , area_width
	mul ebx
	mov ebx , [ebp+arg2]
	add eax , ebx
	shl eax , 2
	add esi, eax
	mov ecx, [ebp+arg4]
	dhl:
	mov dword ptr [esi] , 0 
	add esi , 4
	loop dhl
	popa
	mov esp, ebp
	pop ebp
	ret
draw_horizontal_line endp

draw_vertical_line proc
	push ebp
	mov ebp, esp
	pusha
	mov esi, [ebp+arg1]
	mov eax ,[ebp+arg3]
	mov ebx , area_width
	mul ebx
	mov ebx , [ebp+arg2]
	add eax , ebx
	shl eax , 2
	add esi, eax
	mov ecx, [ebp+arg4]
	dvl:
	mov dword ptr [esi] , 0 
	add esi , area_width
	add esi , area_width
	add esi , area_width
	add esi , area_width
	loop dvl
	popa
	mov esp, ebp
	pop ebp
	ret
draw_vertical_line endp


drawString proc


	mov esi , draw_string
	mov edx , 115
	xor ecx , ecx
		
	et_while_hand_text:
		
		push edx
		push esi

		
		xor eax , eax
		mov al , byte ptr[esi]
		cmp al , 0
		je exit_hand_text_proc
		
		make_text_macro eax , area , edx , 300
		

		pop esi
		pop edx
		
		add edx , 10
		inc esi
		
	loop et_while_hand_text
	
	exit_hand_text_proc:
		pop esi
		pop edx	
		ret


	
drawString endp


draw proc 
	push ebp
	mov ebp, esp
	pusha
	mov eax, [ebp+arg1]
			
	cmp eax, 1
	jz evt_click
	cmp eax, 2
	jz evt_timer ; nu s-a efectuat click pe nimic
	;mai jos e codul care intializeaza fereastra cu pixeli albi
	mov eax, area_width
	mov ebx, area_height
	mul ebx
	shl eax, 2
	push eax
	push 255
	push area
	call memset
	add esp, 12
	
	
	push 270 ;dim
	push 100 ;x
	push 10 ;y
	push area
	call draw_horizontal_line
	add esp, 16
	
	push 270 ;dim
	push 190 ;x
	push 10 ;y
	push area
	call draw_horizontal_line
	add esp, 16
	
	push 270 ;dim
	push 10 ;x
	push 100 ;y
	push area
	call draw_vertical_line
	add esp, 16
	
	push 270 ;dim
	push 10 ;x
	push 190 ;y
	push area
	call draw_vertical_line
	add esp, 16
	
	jmp final_draw
	
evt_click: 
	mov eax, end_game
	cmp eax, 1
	je final_draw

	mov eax, [ebp+arg2]
	cmp eax, 100
	jle put_11
	
	cmp eax, 190
	jle put_101
	
	mov eax, 191
	mov x, eax
	mov eax, 2
	mov x_values, eax
	jmp continueY
	
	put_101:
	mov eax, 101
	mov x, eax
	mov eax, 1
	mov x_values, eax
	jmp continueY
	
	put_11:
	mov eax, 11
	mov x, eax
	mov eax, 0
	mov x_values, eax

	
	continueY:
	mov eax, [ebp+arg3]
	cmp eax, 100
	jle puts_11
	
	cmp eax, 190
	jle puts_101
	
	mov eax, 191
	mov y, eax
	mov eax, 2
	mov y_values, eax
	jmp continueDraw
	
	puts_101:
	mov eax, 101
	mov y, eax
	mov eax, 1
	mov y_values, eax
	jmp continueDraw
	
	puts_11:
	mov eax, 11
	mov y, eax
	mov eax, 0
	mov y_values, eax

	
	continueDraw:
	mov eax, y_values
	mov ebx, 3
	mul ebx
	mov ebx, x_values
	add eax, ebx
	mov esi, offset values
	add esi, eax
	
	xor eax, eax
	mov al, byte ptr[esi]
	cmp al, 40
	jl final_draw
	
	mov eax, symbol
	cmp eax, 0
	je drawZero
	push x
	push y
	push area
	call draw_x
	add esp, 12
	
	mov eax, symbol
	inc eax
	and eax, 1
	mov symbol, eax
	
	
	jmp go_next
	
	drawZero:
	
	push x
	push y
	push area
	call draw_0
	add esp, 12
	
	mov eax, symbol
	inc eax
	and eax, 1
	mov symbol, eax
	
	go_next:
	
	mov eax, y_values
	mov ebx, 3
	mul ebx
	mov ebx, x_values
	add eax, ebx
	
	mov esi, offset values
	add esi, eax
	mov eax, symbol
	inc eax
	and eax, 1
	mov byte ptr[esi], al
	
	call check_win
	cmp eax, 0
	je check_remiza
	call drawString
	mov eax, 1
	mov end_game, eax
	jmp final_draw
		
	check_remiza:
	call check_draw
	cmp eax, 0
	je final_draw
	call drawString
	mov eax,1
	mov end_game, eax
	
	
	
evt_timer:
	inc counter
	
	
	
final_draw:
	popa
	mov esp, ebp
	pop ebp
	ret
	
draw endp

start:
	;alocam memorie pentru zona de desenat
	mov eax, area_width
	mov ebx, area_height
	mul ebx
	shl eax, 2
	push eax
	call malloc
	add esp, 4
	mov area, eax
	
	
	;apelam functia de desenare a ferestrei
	; typedef void (*DrawFunc)(int evt, int x, int y);
	; void __cdecl BeginDrawing(const char *title, int width, int height, unsigned int *area, DrawFunc draw);
	push offset draw
	push area
	push area_height
	push area_width
	push offset window_title
	call BeginDrawing
	add esp, 20
	
	;terminarea programului
	push 0
	call exit
end start