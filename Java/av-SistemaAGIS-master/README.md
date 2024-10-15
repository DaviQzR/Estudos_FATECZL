# Sistema AGIS

# Integrantes

- Davi de Queiroz Romão

- Luiz Antonio da Silva Cruz

# Escopo

Um sistema acadêmico de uma faculdade, chamado AGIS, oferece diversas funcionalidades e
seus usuários são alunos, professores e funcionários da secretaria acadêmica.
Apesar desses perfis, nesse momento, por se tratar de protótipo, o desenvolvimento não
considera a necessidade de login para acesso seguro das áreas de cada usuário.
A secretaria acadêmica necessita cadastrar os alunos que ingressam na faculdade pelo
vestibular. São diversos dados que devem ser incluídos no dia da primeira matrícula, como CPF,
que deve ser válido de acordo com a legislação brasileira, nome, nome social (Não
obrigatório), data de nascimento, telefones de contato, e-mail pessoal, e-mail corporativo,
data de conclusão do segundo grau, instituição de conclusão do segundo grau, pontuação no
vestibular, posição no vestibular, ano de ingresso, semestre de ingresso, semestre e ano limite
de graduação. Todos os alunos devem receber um RA.

Todo aluno está vinculado a apenas 1 curso e 1 turno (Que devem ser preenchidos na ficha do
aluno).

A faculdade tem diversos cursos, que são registrados por 1 código único numérico de 0 a 100,
um nome, uma carga horária, uma sigla para uso interno e a última nota da participação no
ENADE (De acordo com o regimento do Ministério da Educação do Brasil)

Os cursos têm entre 40 e 50 disciplinas, que são registradas por um código numérico iniciado
em 1001, um nome, quantidade de horas semanais. Cada disciplina tem entre 5 e 15 conteúdos
que serão ministrados ao longo de um semestre.

Cursos podem ter disciplinas semelhantes, mas não exatamente iguais, pois podem se
diferenciar no nome, na carga horária ou no conteúdo. As disciplinas estão presentes em um
curso, em um determinado horário

Todos os alunos devem poder se matricular em 1 ou mais disciplinas do seu curso para serem
cursadas ao longo de um semestre. O processo de matrícula significa que o aluno deve
selecionar, dentro de um rol de disciplinas, as que ele pretende cursar. Considere que o aluno
pode, ou não, já ter cursados disciplinas em semestres anteriores, com ou sem aprovação.
Considere que um aluno não pode fazer disciplinas cujos horários conflitem. Considere
também, que matriculas são feitas semestralmente e o sistema deve estar preparado para isso.
